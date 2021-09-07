package model.buy;

import model.product.ProductVO;

public class BuyDAO {
	public int price(ProductVO pvo, BuyVO bvo){
		System.out.println(pvo);
		System.out.println(bvo);
		return pvo.getPprice()*bvo.getBnum();
	}

}
