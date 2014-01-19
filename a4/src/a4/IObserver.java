package a4;

public interface IObserver {
	public void update(IObservable o);
	public void update(IObservable o, Object arg);
}
