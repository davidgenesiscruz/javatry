package org.docksidestage.bizfw.basic.objanimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BarkProcess {

    Logger logger = LoggerFactory.getLogger(BarkProcess.class);
    public BarkedSound bark() {
        breatheIn();
        prepareAbdominalMuscle();
        String barkWord = getBarkWord();
        BarkedSound barkedSound = doBark(barkWord);
        return barkedSound;
    }

    protected void prepareAbdominalMuscle() {
        logger.debug("...Using my abdominal muscle"); // dummy implementation
        postProcessMuscularExertion();
    }

    protected void breatheIn() {
        logger.debug("...Breathing in"); // dummy implementation
        postProcessMuscularExertion();
    }

    protected abstract String getBarkWord();

    protected BarkedSound doBark(String barkWord) {
        postProcessMuscularExertion();
        return new BarkedSound(barkWord);
    }

    protected abstract void postProcessMuscularExertion();
}
