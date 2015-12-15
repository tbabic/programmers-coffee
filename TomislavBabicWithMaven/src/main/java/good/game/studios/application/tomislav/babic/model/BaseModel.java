package good.game.studios.application.tomislav.babic.model;

import good.game.studios.application.tomislav.babic.util.IdGenerator;


/**
 * This class is a base class with unique id (per class type) used for identifying objects
 * 
 * @author Tomislav Babic
 *
 */
public abstract class BaseModel {
	
	private final int id;
	
	public BaseModel() {
		id = IdGenerator.getNewId(getClass());
	}
	
	public int getId() {
		return this.id;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseModel other = (BaseModel) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getCanonicalName()+" [" + id + "]";
	}
}
