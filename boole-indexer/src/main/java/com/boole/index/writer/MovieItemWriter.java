package com.boole.index.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Writer;
import java.util.List;

/**
 * Created using Intellij IDE
 * Created by rnkoaa on 11/25/15.
 */
@Component
public class MovieItemWriter implements ItemWriter<Byte[]> {
    @Override
    public void write(List<? extends Byte[]> items) throws Exception {

    }
}
