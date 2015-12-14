package good.game.studios.application.tomislav.babic.repository;

import good.game.studios.application.tomislav.babic.util.Identifier;

import java.util.HashMap;


public abstract class GenericRepository<T extends Identifier> {

	private HashMap<Integer, T> container = new HashMap<Integer, T>();
	
	public T getById(int id) {
		return container.get(id);
	}
	
	
	
}
