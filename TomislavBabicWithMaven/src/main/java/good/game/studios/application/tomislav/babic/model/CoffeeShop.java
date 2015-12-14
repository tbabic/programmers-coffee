package good.game.studios.application.tomislav.babic.model;

import good.game.studios.application.tomislav.babic.util.Identifier;

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
public class CoffeeShop extends Identifier {

	private Queue<Programmer> typeQueue = new LinkedList<Programmer>();	
	private Queue<Programmer> cashRegisterQueue = new LinkedList<Programmer>();	
	private Queue<Programmer> machineQueue = new LinkedList<Programmer>();
	
	private List<CashRegister> cashRegisters;	
	private List<CoffeeMachine> coffeeMachine;	
	private List<CoffeeTypeTerminal> coffeeTypeTerminals;
	
	private List<Programmer> programmers;
	
	public CoffeeShop(int numberOfCoffeeTypeTerminals, int numberOfCashRegisters, int numberOfCoffeeMachines) {
		newDay(numberOfCoffeeTypeTerminals, numberOfCashRegisters, numberOfCoffeeMachines);
	}
	
	/**
	 * initializes Coffee shop queues and a list of customers
	 * 
	 * @param programmers
	 */
	public void openUp(List<Programmer> programmers) {
		this.programmers = new LinkedList<Programmer>(programmers);
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
	public void getInLine(Programmer programmer) {
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
	public void moveToCashRegister(Programmer programmer) {
		synchronized(cashRegisterQueue) {
			cashRegisterQueue.add(programmer);
			cashRegisterQueue.notify();
		}
	}

	
	/**
	 * adds a new order (programmer and coffee type) to the machine queue
	 * 
	 * @param order
	 */
	public void moveToCoffeeMachine(Programmer programmer) {
		synchronized(machineQueue) {
			machineQueue.add(programmer);
			machineQueue.notify();
		}
	}
	
	/**
	 * removes the programmer from Coffee shop and sends notification to programmers monitor
	 * which is waiting in waitForClosing() method
	 * 
	 * @param programmer
	 */
	public void leave(Programmer programmer) {
			synchronized(programmers) {
				programmers.remove(programmer);
				programmers.notify();
			}
	}
	
	/**
	 *  wait for all programmers to leave the coffee shop then close it
	 *  in case the thread is interrupted, quit everything and throw runtime exception
	 */
	public void waitForClosing() {
		synchronized (programmers) {
			while(!programmers.isEmpty()) {
				try {
					programmers.wait();
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
	public void newDay(int numberOfCoffeeTypeTerminals, int numberOfCashRegisters, int numberOfCoffeeMachines) {
		typeQueue.clear();
		cashRegisterQueue.clear();
		machineQueue.clear();
		cashRegisters = getNewList(CashRegister.class, numberOfCashRegisters, cashRegisterQueue);	
		coffeeMachine = getNewList(CoffeeMachine.class, numberOfCoffeeMachines, machineQueue);	
		coffeeTypeTerminals = getNewList(CoffeeTypeTerminal.class, numberOfCoffeeTypeTerminals, typeQueue);
		if (programmers != null) {
			programmers.clear();
		}
	}
	
	
	public List<CashRegister> getCashRegisters() {
		return cashRegisters;
	}


	public List<CoffeeMachine> getCoffeeMachine() {
		return coffeeMachine;
	}


	public List<CoffeeTypeTerminal> getCoffeeTypeTerminals() {
		return coffeeTypeTerminals;
	}
	
	//private methods for initializing
	
	private <T> List<T> getNewList(Class<T> clazz, int n, Queue<?> queue) {
		try {
		    List<T> list = new ArrayList<T>();
		    for (int i=0; i<n; i++) {
			    Constructor<T> constructor = clazz.getConstructor(Queue.class, CoffeeShop.class);
			    T object = constructor.newInstance(queue, this);
			    list.add(object);
		    }
		    return list;
		} catch (Exception e) {
			throw new RuntimeException("Could not initiliaze list: " + clazz, e);
		}
	}
	
	private void openUpQueueProcessors(List<? extends CoffeeShopQueueProcessor<?>> list) {
		for (CoffeeShopQueueProcessor<?> processor : list) {
			if (!processor.isOpen()) {
				processor.setOpen(true);
				Thread thread = new Thread(processor);
				thread.start();
			}
		}
		
	}
	
	//private methods for closing
	
	private void close() {

		//close processors
		closeQueueProcessors(coffeeTypeTerminals);
		closeQueueProcessors(cashRegisters);
		closeQueueProcessors(coffeeMachine);
		
		//notify all monitors on queues, that will close the threads
		notifyAllForQueue(typeQueue);
		notifyAllForQueue(cashRegisterQueue);
		notifyAllForQueue(machineQueue);	
	}
	
	private void closeQueueProcessors(List<? extends CoffeeShopQueueProcessor<?>> list) {
		for (CoffeeShopQueueProcessor<?> processor : list) {
			processor.setOpen(false);
		}
		
	}
	
	private void notifyAllForQueue(Queue<?> queue) {
		synchronized (queue) {
			queue.notifyAll();
		}
	}
}
