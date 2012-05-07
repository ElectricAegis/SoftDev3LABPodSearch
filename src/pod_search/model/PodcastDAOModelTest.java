package pod_search.model;

public class PodcastDAOModelTest extends PodcastDAOModel {


    private PodcastModel[] testInsert;

    public PodcastDAOModelTest() {
        super("");
    }

    public PodcastModel[] selectQuery(String filter) {
        int total = 2;
        if (testInsert != null) {
            total += testInsert.length;
        } 

        PodcastModel[] testModels = new PodcastModel[total];
        testModels[0] = new PodcastModel("name1","link1",1);
        testModels[1] = new PodcastModel("name2","link2",2);
        for (int i = 2; i < total; i++){
            testModels[i] = testInsert[i-2];
        }

        return testModels;
    }

    public PodcastModel[] selectAll() {
        return selectQuery("");
    }

    //TODO: return 2D array of results
    public void insertQuery(PodcastModel podcast) {
    if (testInsert == null) {
        testInsert = new PodcastModel[1];
        testInsert[0] = podcast;
        return;
    }

    PodcastModel[] tempInsert = new PodcastModel[1 + testInsert.length];
        for (int i = 0; i < tempInsert.length-1; i++){
                tempInsert[i] = testInsert[i];
            }
        tempInsert[tempInsert.length-1] = podcast;
    }

}