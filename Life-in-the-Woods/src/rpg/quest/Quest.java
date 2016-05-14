package rpg.quest;

public abstract class Quest {
	protected String status,name;
	protected int id;
	
	
	
	public Quest(int id, String status,String name){
		this.id = id;
		this.status = status;
		this.name = name;
	}
	
	public void acceptQuest(){
		this.status = "accepted";
	}
	
	public void completeQuest(){
		this.status = "finished";
	}
	
	public void changeStatus(String status){
		this.status = status;
	}
	
	public abstract void giveReward();
	
	public int getId(){
		return id;
	}
	
	public String getStatus(){
		return status;
	}
	
	public String getName(){
		return name;
	}


}
