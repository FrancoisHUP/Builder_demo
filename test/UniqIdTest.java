import Serveur.UniqId;
import org.junit.Test;

import static org.junit.Assert.*;

public class UniqIdTest {

    @Test
    public void createMiltipleIdInShortTime_IdsShouldNotBeTheSame() {
        int numberIdGenerated = 10000;
        UniqId ids [] = new UniqId[numberIdGenerated];
        for(int i = 0; i < numberIdGenerated; i++) {
            ids[i] = new UniqId("nom");
        }

        for(int i = 0; i < numberIdGenerated; i++) {
            for(int j=i+1; j < numberIdGenerated; j++) {
                assertFalse(ids[i].equals(ids[j]));
            }
        }
    }

}
