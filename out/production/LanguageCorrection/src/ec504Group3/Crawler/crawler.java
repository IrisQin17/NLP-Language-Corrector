package ec504Group3.Crawler;

import java.io.IOException;

public interface crawler {
    /**
     * @MODIFY: Find the specific URL File, fetch data from Web, and store data into new File
     */
    void StoreFile(String urlAddress,int count) throws IOException;
}
