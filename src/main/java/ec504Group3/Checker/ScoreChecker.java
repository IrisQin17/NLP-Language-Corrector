package ec504Group3.Checker;

import ec504Group3.Checker.checker;

import java.io.File;

public class ScoreChecker implements checker {
    @Override
    public int check(String checkFile){
        File checkF = new File(checkFile);
        if (checkF.exists()) return 1;
        return 0;
    }
}
