package finalGame.objects;
/*
 * Author:
 * 		Zach
 */
public class PlayerLinkedList {
	
	private PlayerLinkedList nextNode;
	private PlayerLinkedList lastNode;
	private Player data;
	private boolean flag;
	
	private static PlayerLinkedList head;
	private static PlayerLinkedList tail;
	
	public PlayerLinkedList() {
		nextNode = this;
		lastNode = this;
		data = null;
		flag = true;
	}
	
	private PlayerLinkedList(Player d) {
		nextNode = null;
		lastNode = null;
		data = d;
		flag = false;
	}
	
	public void add(Player node) {
		// by default, if "flag" is true...
		if (flag) {
			data = node;
			
			head = this;
			tail = this;
			
			head.nextNode = tail;
			head.lastNode = tail;
			tail.nextNode = head;
			tail.lastNode = head;
			
			flag = false;
		} else {
			PlayerLinkedList newNode = new PlayerLinkedList(node);
			//newNode.data = node;
			newNode.nextNode = head;
			newNode.lastNode = tail;
			tail.nextNode = newNode;
			tail = newNode;
			head.lastNode = tail;
		}
		
		return;
	}
	
	public PlayerLinkedList next() {
		return this.nextNode;
	}
	
	public PlayerLinkedList last() {
		return this.lastNode;
	}
	
	public Player getPlayer() {
		return this.data;
	}
	
	public void remove() {
		lastNode.nextNode = this.nextNode;
		nextNode.lastNode = this.lastNode;
	}
	
	/*
	public static void main(String[] args) {
		PlayerLinkedList list = new PlayerLinkedList();
		
		for (int i = 0; i <= 10; i++) {
			list.add(i);
			
		}
		
		for (int i = 0; i <= 15; i++) {
			System.out.println(list.getValue());
			list = list.next();
		}
		
		list = list.last();
		for (int i = 0; i <= 10; i++) {
			list = list.last();
			System.out.println(list.getValue());
		}
		
		list.remove();
		
		list = list.next();
		for (int i = 0; i <= 15; i++) {
			System.out.println(list.getValue());
			list = list.next();
		}
	}
	*/
}
