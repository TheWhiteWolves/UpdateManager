package updatemanager.common.checking;

import updatemanager.common.UpdateManagerMod;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ListIterator;

/**
 * Splits the version in the numbers representing it, and if returns UMVersion >= webVersion.
 * 
 * @author Vazkii, TheWhiteWolves, Gorion91
 */
public class CMNumerical extends CheckingMethod {

	String numbers = "0123456789";
	String[] knownSplitters= new String[]{"\\.", "_", ",", "-", "/"};
	
	@Override
	public boolean isUpdated(UpdateManagerMod mod, String webVersion) {
		
		String[] webVersions = splitString(webVersion, knownSplitters);
		String[] umVersions = splitString(mod.getUMVersion(), knownSplitters);
 		
		for(int i = 0; i < umVersions.length; i++){
			try {
				int umNumber = Integer.parseInt(umVersions[i]);
				int webNumber = Integer.parseInt(webVersions[i]);
				
				if(webNumber > umNumber)
					return false;
				
			} catch (NumberFormatException e){}
			  catch (ArrayIndexOutOfBoundsException e) { 
				return false; 
				}
		}
		
		return true;
	}
	
	public String[] splitString(String toSplit, String[] knownSplitters){
		ArrayList localTemp = new ArrayList();
		localTemp.add(toSplit);
	
		for(int i=0; i<knownSplitters.length; i++){
			ArrayList temp=new ArrayList();
			ListIterator lis= localTemp.listIterator(0);
			while(lis.hasNext()){
				String[] splitted=((String)lis.next()).split(knownSplitters[i]);
				temp.addAll(Arrays.asList(splitted));
			}
			localTemp=temp;
		}
	
		String[] finallySplitted = new String[localTemp.size()];
	
		ListIterator li = localTemp.listIterator(0);
		while(li.hasNext()){
			finallySplitted[li.nextIndex()]=(String)li.next();
		}
	
		return finallySplitted;
	}

}
