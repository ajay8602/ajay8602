package models;

public class State{
	//############ Fields #####################
	private Integer stateId;
	private String state;

	//############ Constructors ###############
	public State(){
		super();
	}

	//############ Getter-Setters #############
	public void setStateId(Integer stateId){
		this.stateId = stateId;
	}

	public Integer getStateId(){
		return stateId;
	}

	public void setState(String state){
		this.state = state;
	}

	public String getState(){
		return state;
	}
}