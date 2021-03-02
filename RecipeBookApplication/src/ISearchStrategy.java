import java.util.ArrayList;

public interface ISearchStrategy {
    public void RequestSearchParameterFromUser();
    public ArrayList<Recipe> GetSearchResults (ArrayList<Recipe> listToSearch);
}
