package packEuskoFlix;

import java.util.HashMap;

public class UserList {
	private HashMap<Integer,User> list;
	private String id;
	
	public UserList(String pId) {
		this.id = pId;
		this.list = new HashMap<Integer,User>();
	}
	
	//TODO
	public void add(Integer pID,User pUser) {
		if (!this.contains(pUser)) {
			this.list.put(pID, pUser);
		}
	}
	
	//TODO
	public void remove(User pUser) {}
	
	//TODO
	public boolean contains(User pUser) {
		return this.list.containsValue(pUser);
	}
	public void printUsers() {
		User[] myUsers=this.list.values().toArray(new User[this.list.size()]);
		this.sortByID(myUsers);
		int size=myUsers.length;
		for (int i=0;i<size;i++) {
			myUsers[i].printUser();
		}
		
	}
	private void sortByID(User[] myUsers) {
		//METODO TEMPORAL PARA COMPROBAR QUE SE IMPORTAN BIEN LAS PELICULAS
		sortByID(myUsers,0,myUsers.length-1);
	}
	private void sortByID(User[] myUsers, int start, int end) {
		if (end-start>0) {
			int iSwap= swap(myUsers,start,end);
			sortByID(myUsers,start,iSwap-1);
			sortByID(myUsers,iSwap+1,end);
		}
	}
	private int swap(User[] myUsers, int start,int end) {
		User pivot= myUsers[start];
		int left= start;
		int right= end;
		while (left<right) {
			while(myUsers[left].compareTo(pivot)<=0 && left<right)
				left++;
			while(myUsers[right].compareTo(pivot)>0)
				right--;
			if(left<right)
				swap(myUsers,left,right);
		}
		myUsers[start]=myUsers[right];
		myUsers[right]=pivot;
		return right;
	}
}