package com.example.concordion.cdlibrary.di;

import dagger.Module;
import dagger.Provides;

import javax.inject.Named;

@Module
public class NameModule {

    @Provides @Named("a")
    static String createName(){
        return "Freddy Kruger";
    }
}
