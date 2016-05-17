package rpg.quest;

import org.json.simple.JSONObject;

import rpg.json.JSONDecoder;

public class Quest {
	public static Quests[] quests;
	
	
	public Quest() {
		quests = new Quests[60];
		loadQuests();
	}
	
	private void loadQuests() {
		JSONObject obj = JSONDecoder.loadData("res/Savegames/quests.json");
		
		
		/*
		
		Long Quest_ID = (Long) ((JSONObject) (obj.get("1"))).get("id");
		String Quest_name = (String) ((JSONObject) (obj.get("1"))).get("name");
		String Quest_task = (String) ((JSONObject) (obj.get("1"))).get("task");
		String Quest_status = (String) ((JSONObject) (obj.get("1"))).get("status");
		Long Quest_bounty = (Long) ((JSONObject) (obj.get("1"))).get("bounty");
		Long Quest_bounty_amount = (Long) ((JSONObject) (obj.get("1"))).get("bounty_amount");
		Long Quest_progress = (Long) ((JSONObject) (obj.get("1"))).get("progress");
		
		System.out.println("Quest ID " + Quest_ID);
		System.out.println("Quest Name " + Quest_name);
		System.out.println("Quest Task " + Quest_task);
		System.out.println("Quest Status " + Quest_status);
		System.out.println("Quest bounty " + Quest_bounty);
		System.out.println("Quest bounty_amount " + Quest_bounty_amount);
		System.out.println("Quest progress " + Quest_progress);
		
		*/
		
		
		
		for (int i = 0; i < quests.length; i++) {
			int slot = i + 1;
			JSONObject item = (JSONObject) obj.get(String.valueOf(slot));
			if(item == null) continue;
			
			String name = (String)(item.get("name"));
			String status = (String)(item.get("status"));
			String task = (String)(item.get("task"));
			int bounty = (int)((long)item.get("bounty"));
			int bounty_amount = (int)((long)item.get("bounty_amount"));
			int progress = (int)((long)item.get("progress"));
			int id = (int)((long)item.get("id"));
			
			

			if (id == 1) {
				quests[i] = new testquest(name, status,task,bounty,bounty_amount,progress,id);
			} else if(id == 2) {
				quests[i] = new testquest(name, status,task,bounty,bounty_amount,progress,id);
			} else{
				System.out.println("fehler");
				System.out.println(id);
			}
			
		}
	}
	public int getId(int i){
		return quests[i-1].getId();
	}
	
	public String getName(int i){
		return quests[i-1].getName();
	}
	
	public String getStatus(int i){
		return quests[i-1].getStatus();
	}
	
	public String getTask(int i){
		return quests[i-1].getTask();
	}
	
	public int getBounty(int i){
		return quests[i-1].getBounty();
	}
	
	public int getBountyAmount(int i){
		return quests[i-1].getBounty_amount();
	}
	public int getProgress(int i){
		return quests[i-1].getProgress();
	}
}
