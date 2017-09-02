package com.p2p.webapp.common.util;

import java.util.Map;

import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ScriptEngineManagerTool {
    public static ScriptEngineManager manager = new ScriptEngineManager(); 

    public ScriptEngineManagerTool(){    	
    }
    
    public static void execScript(Map map , String script){
    	ScriptEngine engine = manager.getEngineByName("JavaScript");
    	Bindings bindings = engine.createBindings();
    	bindings.putAll(map);
    	try {
			engine.eval(script,bindings);
		} catch (ScriptException e) {
			e.printStackTrace();
		}
    }
    
}
