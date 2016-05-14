package rpg.quest;

public abstract class Quests {
	
	
	protected String name,status,task;
	protected int bounty,bounty_amount,progress,id;
	

	public Quests(String name, String status, String task, int bounty, int bounty_amount, int progress, int id) {
		this.name = name;
		this.status = status;
		this.task = task;
		this.bounty = bounty;
		this.bounty_amount = bounty_amount;
		this.progress = progress;
		this.id = id;
	}
	
	public abstract void actions();
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public int getBounty() {
		return bounty;
	}

	public void setBounty(int bounty) {
		this.bounty = bounty;
	}

	public int getBounty_amount() {
		return bounty_amount;
	}

	public void setBounty_amount(int bounty_amount) {
		this.bounty_amount = bounty_amount;
	}

	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName(){
		return this.name;
	}
 }
