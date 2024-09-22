package dev.xoapp.kitty.utils;

import dev.xoapp.kitty.factory.ModuleFactory;

public class Kitty {

    public static String appendModules() {
        return String.join(", ", ModuleFactory.getModules().keySet());
    }
}
