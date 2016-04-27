package model.skills;

import java.io.Serializable;

import model.board.Square;
import model.piece.Piece;
import utils.GameConfig;
import view.DialogView;
/**
 * Class that increases damage of piece's attack action
 * @author Daniel
 *
 */
public class AttackSkill extends Skill implements IPerformTraitSkill, Serializable {

	public AttackSkill() {
		// TODO Auto-generated constructor stub
		super.setName("Attack");
	}
	
	@Override
	public void performSkill(Piece skillOwner) {		
		skillOwner.getTraitSet().getDamageTrait().modifyValue(GameConfig.getDamagetraitmultiplier());
		DialogView.getInstance().showInformation("Attack damage increased!");
	}
	
}
