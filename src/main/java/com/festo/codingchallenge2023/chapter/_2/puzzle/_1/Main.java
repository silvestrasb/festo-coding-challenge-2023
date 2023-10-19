package com.festo.codingchallenge2023.chapter._2.puzzle._1;

import com.festo.codingchallenge2023.chapter.util.general.file_util.PathResolver;
import com.festo.codingchallenge2023.chapter.util.keymaker.file.KeyMakerFileReader;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Hammer;
import com.festo.codingchallenge2023.chapter.util.keymaker.service.HammerService;
import com.festo.codingchallenge2023.chapter.util.keymaker.service.KeyService;

import java.util.List;

public class Main {

    public static String KEYMAKER_HAMMER_CONFIG_REL_PATH = "src\\com\\festo\\codingchallenge2023\\chapter\\_2\\puzzle\\_1\\resource\\hammer_collection.txt";
    public static String FORGED_KEY_LIST_CONFIG_REL_PATH = "src\\com\\festo\\codingchallenge2023\\chapter\\_2\\puzzle\\_1\\resource\\21_keymaker_forge.txt";


    public static void main(String[] args) {
        List<Hammer> hammerList = KeyMakerFileReader.initializeHammerList(PathResolver.getAbsPath(KEYMAKER_HAMMER_CONFIG_REL_PATH));
        List<String> keyList = KeyMakerFileReader.initializeForgedKeyList(PathResolver.getAbsPath(FORGED_KEY_LIST_CONFIG_REL_PATH));
        HammerService hammerService = new HammerService(hammerList);
        KeyService keyService = new KeyService(hammerService);

        keyList.stream()
                .filter(key -> keyService.reduceKey(key).contains(KeyService.keyBaseSegment))
                .forEach(key -> System.out.printf(
                        "Key %s can be constructed from baseSegment \"%s\", using provided hammer list.",
                        key, KeyService.keyBaseSegment
                ));

    }
}
