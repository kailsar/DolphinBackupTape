/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dolphinbackuptape;

/**
 *
 * @author Christopher
 */
public class DolphinBackupTape {
    static BackupTapeController btc = null;
    static StartFrame homeScreen = null;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       btc = new BackupTapeController();
       btc.establishConnection("dolphin","dolphin99");
       homeScreen = new StartFrame();
       homeScreen.setVisible(true);

    }
    public static BackupTapeController getBackupTapeController () {
        return btc;
    }
    public static StartFrame getHomeScreen () {
        return homeScreen;
    }
}
