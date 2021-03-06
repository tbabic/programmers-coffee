package good.game.studios.application.tomislav.babic.model;

import good.game.studios.application.tomislav.babic.util.Constants;

import java.text.MessageFormat;
import java.util.Queue;

import org.apache.log4j.Logger;


/**
 * This class is a generic class and abstraction for queue processing
 * 
 * @author Tomislav Babic
 *
 * @param <T>
 */
public abstract class CoffeeShopQueueProcessor<T> extends BaseModel implements Runnable{

	private static final Logger logger = Logger.getLogger(Constants.LOGGER_NAME);
	
	private Queue<T> queue;
	private int processedProgrammers = 0;
	private boolean open = false;
	protected CoffeeShop coffeeShop;
	
	public CoffeeShopQueueProcessor(Queue<T> queue, CoffeeShop coffeeShop) {
		super();
		this.queue = queue;
		this.coffeeShop = coffeeShop;
	}
	
	@Override
	public void run() {
		processQueue();
	}
	
	/**
	 *  this method is responsible for taking the next element from queue 
	 *  and then processing it per implementation in processCustomer(T object) method
	 */
	public void processQueue() {
		while(open) {
			T object = getNextObject();
			//process next customer in queue
			if (object != null) {
				processQueueElement(object);
			} 
		}
	}
	
	private void processQueueElement(T object) {
		try {
			processCustomer(object);
		} catch (InterruptedException e) {
			logger.error(MessageFormat.format("{0} couldn't process {1} so return him back to the end of the queue", this, object));
			synchronized(queue) {
				queue.add(object);
			}
			open = false;
			return; //exit from thread
		}
		processedProgrammers++;
	}

	private T getNextObject() {
		T object = null;
		synchronized(queue) {
			while(queue.isEmpty() && open) {
				try {
					queue.wait();
				} catch (InterruptedException e) {
					logger.error(MessageFormat.format("{0} broke down", this));
					open = false;
					return null; //exit from thread
				}
			}
			if (!queue.isEmpty()) {
				object = queue.remove();
			}
		}
		return object;
	}
	
	public int getProcessedProgrammers() {
		return this.processedProgrammers;
	}

	public boolean isOpen() {
		return open;
	}
	
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	
	/**
	 * This method will process customer (queue element)
	 * 
	 * @param object
	 * @throws InterruptedException
	 */
	abstract protected void processCustomer(T object) throws InterruptedException;

	
	
	
	
}
