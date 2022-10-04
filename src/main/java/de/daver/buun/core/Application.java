package de.daver.buun.core;

import java.io.File;

public interface Application {

    boolean onStart();

    void onStop();

    File getRootDir();

    String getVersion();

    String getName();

}
