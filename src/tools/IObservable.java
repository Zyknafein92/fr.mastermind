package tools;

public interface IObservable {

	public void addObserver(IObserver obs);
	public void notifyObserver();
}
