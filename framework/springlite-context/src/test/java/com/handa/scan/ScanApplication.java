package com.handa.scan;

import com.handa.imported.LocalDateConfiguration;
import com.handa.imported.ZonedDateConfiguration;
import com.handa.springlite.annotation.ComponentScan;
import com.handa.springlite.annotation.Import;

@ComponentScan
@Import({ LocalDateConfiguration.class, ZonedDateConfiguration.class })
public class ScanApplication {

}
