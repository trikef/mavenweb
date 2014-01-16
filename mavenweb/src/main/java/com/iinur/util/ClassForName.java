package com.iinur.util;

public class ClassForName {

	public static Object InstanceForName(String className){
		Object o = null;
        try {  
            @SuppressWarnings("rawtypes")
			Class clazz = Class.forName(className);    //文字列からClass取得  
            if (clazz != null) {  
                o = clazz.newInstance();    //Classによって新しいインスタンス生成  
            }  
        } catch (ClassNotFoundException e) {    //by Class.forName  
            //指定した名称のクラス存在しなかった場合  
            e.printStackTrace();  
        } catch (InstantiationException e) {    //by newInstance()  
            //インスタンス作成不可の場合（パラメータなしのコンストラクタ存在しない場合 - new Xxxx()でエラーの場合）  
            e.printStackTrace();  
        } catch (IllegalAccessException e) {    //by newInstance()  
            //権限はないとき  
            e.printStackTrace();  
        }  
        return o;
	}
}
