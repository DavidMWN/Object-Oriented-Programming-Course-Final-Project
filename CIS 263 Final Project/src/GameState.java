
public interface GameState
{
	public void enterRoom();
	
	public void inputProcessing(int input);
	
	public boolean getCompleteFriend();
	
	public boolean getCompleteBattle();
	
	public void resetRoomComplete();
}
