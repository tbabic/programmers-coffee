package good.game.studios.application.tomislav.babic.model;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


/**
 * This class basically serves as a queue manager between queues for choosing coffee type, cash registers and coffee machines
 * 
 * @author Tomislav Babic
 *
 */
public class CoffeeShop {

	private static final int NUMBER_OF_CASH_REGISTERS = 5;	
	private static final int NUMBER_OF_COFFEE_TYPE_TERMINALS = 10;
	private static final int NUMBER_OF_COFFEE_MACHINES = 2;
	
	private static Queue<Programmer> typeQueue = new LinkedList<Programmer>();	
	private static Queue<Order> cashRegisterQueue = new LinkedList<Order>();	
	private static Queue<Order> machineQueue = new LinkedList<Order>();
	
	private static List<CashRegister> cashRegisters = getNewList(CashRegister.class, NUMBER_OF_CASH_REGISTERS, cashRegisterQueue);	
	private static List<CoffeeMachine> coffeeMachine = getNewList(CoffeeMachine.class, NUMBER_OF_COFFEE_MACHINES, machineQueue);	
	private static List<CoffeeTypeTerminal> coffeeTypeTerminals = getNewList(CoffeeTypeTerminal.class, NUMBER_OF_COFFEE_TYPE_TERMINALS, typeQueue);
	
	private static List<Programmer> programmers;
	
	/**
	 * initializes Coffee shop queues and a list of customers
	 * 
	 * @param programmers
	 */
	public static void openUp(List<Programmer> programmers) {
		CoffeeShop.programmers = new LinkedList<Programmer>(programmers);
		openUpQueueProcessors(coffeeTypeTerminals);
		openUpQueueProcessors(cashRegisters);
		openUpQueueProcessors(coffeeMachine);
		
	}
	
	/**
	 * adds a new programmer to the queue for choosing coffee type
	 * 
	 * @param programmer
	 * 
	 */
	public static void getInLine(Programmer programmer) {
		synchronized(typeQueue) {
			typeQueue.add(programmer);
			typeQueue.notify();
		}		
	}
	
	/**
	 * adds a new programmer and chosen coffee type to the cash register queue
	 * 
	 * @param programmer
	 * @param coffeeType
	 */
	public static void moveToCashRegister(Programmer programmer, CoffeeType coffeeType) {
		Order order = new Order(programmer, coffeeType);
		synchronized(cashRegisterQueue) {
			cashRegisterQueue.add(order);
			cashRegisterQueue.notify();
		}
	}

	
	/**
	 * adds a new order (programmer and coffee type) to the machine queue
	 * 
	 * @param order
	 */
	public static void moveToCoffeeMachine(Order order) {
		synchronized (machineQueue) {
			machineQueue.add(order);
			machineQueue.notify();
		}
	}
	
	/**
	 * removes the programmer from Coffee shop and sends notification to programmers monitor
	 * which is waiting in waitForClosing() method
	 * 
	 * @param programmer
	 */
	public synchronized static void leave(Programmer programmer) {
			synchronized(programmers) {
				programmers.remove(programmer);
				programmers.notify();
			}
	}
	
	/**
	 *  wait for all programmers to leave the coffee shop then close it
	 *  in case the thread is interrupted, quit everything and throw runtime exception
	 */
	public static void waitForClosing() {
		synchronized (CoffeeShop.programmers) {
			while(!CoffeeShop.programmers.isEmpty()) {
				try {
					CoffeeShop.programmers.wait();
				} catch (InterruptedException e) {
					//close all threads on queue processors
					close();
					throw new RuntimeException("thread interrupted while waiting for coffee shop to be closed", e);
					
				}
			}
			close();
		}
	}
	
	/**
	 *  this method resets all queues, queue processors and list of customers to initial setting
	 */
	public static void reset() {
		typeQueue.clear();
		cashRegisterQueue.clear();
		machineQueue.clear();
		cashRegisters = getNewList(CashRegister.class, NUMBER_OF_CASH_REGISTERS, cashRegisterQueue);	
		coffeeMachine = getNewList(CoffeeMachine.class, NUMBER_OF_COFFEE_MACHINES, machineQueue);	
		coffeeTypeTerminals = getNewList(CoffeeTypeTerminal.class, NUMBER_OF_COFFEE_TYPE_TERMINALS, typeQueue);
		programmers.clear();
	}
	
	
	public static List<CashRegister> getCashRegisters() {
		return cashRegisters;
	}


	public static List<CoffeeMachine> getCoffeeMachine() {
		return coffeeMachine;
	}


	public static List<CoffeeTypeTerminal> getCoffeeTypeTerminals() {
		return coffeeTypeTerminals;
	}
	
	//private methods for initializing
	
	private static <T> List<T> getNewList(Class<T> clazz, int n, Queue<?> queue) {
		try {
		    List<T> list = new ArrayList<T>();
		    for (int i=0; i<n; i++) {
			    Constructor<T> constructor = clazz.getConstructor(Queue.class);
			    T object = constructor.newInstance(queue);
			    list.add(object);
		    }
		    return list;
		} catch (Exception e) {
			throw new RuntimeException("Could not initiliaze list: " + clazz, e);
		}
	}
	
	private static void openUpQueueProcessors(List<? extends CoffeeShopQueueProcessor<?>> list) {
		for (CoffeeShopQueueProcessor<?> processor : list) {
			if (!processor.isOpen()) {
				processor.setOpen(true);
				Thread thread = new Thread(processor);
				thread.start();
			}
		}
		
	}
	
	//private methods for closing
	
	private static void close() {

		//close processors
		closeQueueProcessors(coffeeTypeTerminals);
		closeQueueProcessors(cashRegisters);
		closeQueueProcessors(coffeeMachine);
		
		//notify all monitors on queues, that will close the threads
		notifyAllForQueue(typeQueue);
		notifyAllForQueue(cashRegisterQueue);
		notifyAllForQueue(machineQueue);	
	}
	
	private static void closeQueueProcessors(List<? extends CoffeeShopQueueProcessor<?>> list) {
		for (CoffeeShopQueueProcessor<?> processor : list) {
			processor.setOpen(false);
		}
		
	}
	
	private static void notifyAllForQueue(Queue<?> queue) {
		synchronized (queue) {
			queue.notifyAll();
		}
	}
}
