package Logica.Entidades.Naves;
import Logica.Entidades.Aliens.*;

import java.awt.*;

public abstract class ObjetoColisionable {
	protected Rectangle hitbox;

	public ObjetoColisionable(Rectangle h){
		hitbox = h;
	}

	public abstract void accept(Visitor v);

	public Rectangle getHitBox(){
		return hitbox;
	}
}
