package ultraextreme.util;

public class Resources {
	
	private Resources instance;

	private Resources()
	{
	}
	
	public Resources getInstance()
	{
		if (instance == null)
		{
			instance = new Resources();
		}
		return instance;
	}
}
