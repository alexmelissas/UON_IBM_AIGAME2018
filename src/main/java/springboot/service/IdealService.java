package springboot.service;

import springboot.domain.Ideal;

public interface IdealService {
	public void addIdeal(Ideal ideal);

	public Iterable<Ideal> getIdeals();

	public Ideal getIdealById(String id);

	public void initialIdeal(String id, Ideal newIdeal);

	public void deleteIdealById(String id);

	public void initialPlayer(String id, Ideal ideal);

	boolean isExist(String id);
}
