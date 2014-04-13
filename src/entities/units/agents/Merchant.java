package entities.units.agents;

import control.Player;
import control.UnitType;
import entities.GameObjectType;
import entities.gameboard.GameBoard;
import entities.stats.BaseStatsEnum;
import entities.stats.UnitStats;
import entities.units.Agent;

public class Merchant extends Agent{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8984570260940183249L;

	public Merchant(int id, int playerId, UnitStats new_stats, float xco, float yco, 
			int price, int number, int currency) {
		super(id, playerId, BaseStatsEnum.MERCHANT, new_stats, GameObjectType.UNIT, UnitType.MERCHANT,  xco,
				 yco);
		// TODO Auto-generated constructor stub
	}
	
	public Merchant() {}
	
	@Override
	public void update(Player p, GameBoard gb) {
		// TODO Auto-generated method stub
		
	}
	public int getPrestige(Player p){
		return p.prestige.get(this);
	}
	
	public void addPrestige(int i,Player p){
		p.prestige.put(this, this.getPrestige(p)+i);
	}
	
	public void buy(Goods goods, int number, Player p){
		if(this.getPrestige(p)<=50)
			System.out.println("The merchant doen't wanna sell it to you");
		else
		{
			p.goodsNumber.put(goods, p.getGoodsNumber(goods)+number);
			p.resources.Gold -= number * goods.price;
		}
	}
}
