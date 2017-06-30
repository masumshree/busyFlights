package com.travix.medusa.airlineService.utility;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.travix.medusa.busyflights.domain.crazyair.CrazyAirResponse;
import com.travix.medusa.busyflights.domain.toughjet.ToughJetResponse;


public class AirLineSortedOrder implements Comparator<Object> {
	/*
	 * This method is used to sort fare.
	 */
	@Override
	public int compare(Object obj1, Object obj2) {
		
			{
				CrazyAirResponse crazyAirResponse11=null;
				CrazyAirResponse crazyAirResponse12=null;
				ToughJetResponse toughJetResponse21=null;
				ToughJetResponse toughJetResponsej22=null;
				if(obj1 instanceof CrazyAirResponse )
					crazyAirResponse11=(CrazyAirResponse)obj1;
				else if(obj1 instanceof ToughJetResponse )
					toughJetResponse21=(ToughJetResponse)obj1;
				
				
				if(obj2 instanceof CrazyAirResponse )
					crazyAirResponse12=(CrazyAirResponse)obj2;
				else if(obj2 instanceof ToughJetResponse )
					toughJetResponsej22=(ToughJetResponse)obj2;
				
				
				if((crazyAirResponse11==null?toughJetResponse21.getBasePrice()+toughJetResponse21.getTax()-(toughJetResponse21.getBasePrice()*(toughJetResponse21.getDiscount()/100)):crazyAirResponse11.getPrice()) > (toughJetResponsej22==null?crazyAirResponse12.getPrice():toughJetResponsej22.getBasePrice()+toughJetResponsej22.getTax()-(toughJetResponsej22.getBasePrice()*(toughJetResponsej22.getDiscount()/100)))){
					 return 1;
				} else {
			    	return -1;
			    }
			}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void sortAllFlightList(List list){
		Collections.sort(list,new AirLineSortedOrder());
	}

}
