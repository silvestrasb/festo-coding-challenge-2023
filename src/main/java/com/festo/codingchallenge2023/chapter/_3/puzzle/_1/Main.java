package com.festo.codingchallenge2023.chapter._3.puzzle._1;

import com.festo.codingchallenge2023.chapter.util.general.file_util.PathResolver;
import com.festo.codingchallenge2023.chapter.util.keymaker.file.KeyMakerFileReader;
import com.festo.codingchallenge2023.chapter.util.keymaker.model.Hammer;
import com.festo.codingchallenge2023.chapter.util.keymaker.service.HammerService;
import com.festo.codingchallenge2023.chapter.util.keymaker.service.KeyService;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Same code as for 2.1 solution.
 */
public class Main {

    public static String KEYMAKER_HAMMER_CONFIG_REL_PATH = "src\\main\\java\\com\\festo\\codingchallenge2023\\chapter\\_3\\puzzle\\_1\\resource\\hammer_collection.txt";
    public static String FORGED_KEY_LIST_CONFIG_REL_PATH = "src\\main\\java\\com\\festo\\codingchallenge2023\\chapter\\_3\\puzzle\\_1\\resource\\31_keymaker_forge_2.txt";


    public static void main(String[] args) {
        List<Hammer> hammerList = KeyMakerFileReader.initializeHammerList(PathResolver.getAbsPath(KEYMAKER_HAMMER_CONFIG_REL_PATH));
        List<String> keyList = KeyMakerFileReader.initializeForgedKeyList(PathResolver.getAbsPath(FORGED_KEY_LIST_CONFIG_REL_PATH));
        HammerService hammerService = new HammerService(hammerList);
        KeyService keyService = new KeyService(hammerService);

        AtomicReference<Integer> index = new AtomicReference<>(1);
        Integer listSize = keyList.size();
        List<String> validKeyList = keyList.parallelStream()
                .peek(key -> System.out.printf("(%d/%d) Key: %s -- %s\n", index.get(), listSize, key, Thread.currentThread().getName()))
                .peek(placeholder -> index.getAndSet(index.get() + 1))
                .filter(key -> {
                    boolean contains = keyService.reduceKey(key).contains(KeyService.keyBaseSegment);
                    keyService.clearCache();
                    return contains;
                }).toList();

        validKeyList
                .forEach(key -> System.out.printf(
                        "Key %s can be constructed from baseSegment \"%s\", using provided hammer list.",
                        key, KeyService.keyBaseSegment
                ));


    }
}
