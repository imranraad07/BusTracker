package com.example.imran.simpleapp;

public class MyHabizabiClass {

    public static String myRoute , mySchdedule , myStoppage ;
    public static String driverRouteID ;
    public static String myPredictionTime ;
    public static double myLatitude , myLongitude ;

    public static void setMyRoute(String s){
        myRoute = s ;
    }
    public static void setMyStoppage(String s){
        myStoppage = s ;
    }
    public static void setMySchdedule(String s){
        mySchdedule = s ;
    }
    public static String getMyRoute(){
        return myRoute;
    }
    public static String getMyStoppage(){
        return myStoppage;
    }
    public static String getMyStoppageInEnglish(String stopagge){
        if( stopagge.equals( "কাজিপাড়া" ) )	return "kazipara" ;
        if( stopagge.equals("শেওড়াপাড়া" )) 	return "shewrapara" ;
        if( stopagge.equals("তালতলা"  )) 	return "taltola" ;
        if( stopagge.equals("মিরপুর-১০"  ) )	return "mirpur-10" ;
        if( stopagge.equals("মিরপুর-১৪" ) )	return "mirpur-14" ;
        if( stopagge.equals("মিরপুর-১২" )) 	return "mirpur-12" ;
        if( stopagge.equals("মিরপুর-১" )) 	return "mirpur-1" ;
        if( stopagge.equals("মিরপুর-২" ) )	return "mirpur-2" ;
        if( stopagge.equals("শেরে বাংলা স্টোডিয়াম"  )) 	return "kazipara" ;
        if( stopagge.equals("শ্যামলী" )) 	return "shyamoli" ;
        if( stopagge.equals("মুগদা" )) 	return "mughda" ;
        if( stopagge.equals("বৌদ্ধ মন্দির") ) 	return "bouddho mondir" ;
        if( stopagge.equals("খিলগাঁও" )) 	return "khilgao" ;
        if( stopagge.equals("বাসাবো" )) 	return "basabo" ;
        if( stopagge.equals("মোহাম্মদপুর বাস স্ট্যান্ড" ) )	return "mohammadpur bus stand" ;
        if( stopagge.equals("সঙ্কর" )) 	return "shonkor" ;
        if( stopagge.equals("সাত মসজিদ রোড") ) 	return "sat mosjid road" ;
        if( stopagge.equals("জিগাতলা"  )) 	return "jigatola" ;
        if( stopagge.equals("ঢানমন্ডি- ১৫") ) 	return "dhanmondi-15" ;
        return "NULL";
    }
    public static String getMySchdedule(){
        return mySchdedule;
    }
    public static void setdriverRouteID (String s){
        driverRouteID = s ;
    }
    public static String getdriverRouteID (){
        return driverRouteID;
    }
    public static void setMyPredictionTime(String s){
        myPredictionTime = s ;
    }
    public static String getMyPredictionTime(){
        return myPredictionTime ;
    }
    public static void setMyLatitude(String s){
        myLatitude = Double.parseDouble(s) ;
    }
    public static double getMyLatitude(){
        return myLatitude ;
    }
    public static void setMyLongitude(String s){
        myLongitude = Double.parseDouble(s) ;
    }
    public static double getMyLongitude(){
        return myLongitude ;
    }
    public static String retBusId(String BusName , String ScheduleTime){
        if(BusName.equals("বৈশাখি")) {
            if(ScheduleTime.equals( "6:45 AM")) return "105" ;
            if(ScheduleTime.equals("7:30 AM")) return "104" ;
            if(ScheduleTime.equals("8:10 AM")) return "103" ;
            if(ScheduleTime.equals("8:50 AM")) return "102" ;
            if(ScheduleTime.equals("9:45 AM")) return "101" ;
        }
        else if(BusName.equals( "চৈতালি")){
            if(ScheduleTime.equals("6:50 AM - a"))return "209";
            if(ScheduleTime.equals("6:50 AM - b"))return "208";
            if(ScheduleTime.equals("7:30 AM - a"))return "207";
            if(ScheduleTime.equals("7:30 AM - b"))return "206";
            if(ScheduleTime.equals("8:30 AM - a"))return "205";
            if(ScheduleTime.equals("8:30 AM - b"))return "204";
            if(ScheduleTime.equals("8:40 AM (stuff)"))return "203";
            if(ScheduleTime.equals("9:50 AM - a"))return "202";
            if(ScheduleTime.equals("9:50 AM - b"))return "201";
        }
        else if(BusName.equals("শ্রাবণ")) {
            if(ScheduleTime.equals("7:20 AM")) return "304" ;
            if(ScheduleTime.equals("8:15 AM")) return "303" ;
            if(ScheduleTime.equals("9:00 AM")) return "302" ;
            if(ScheduleTime.equals("10:00 AM")) return "301" ;
        }
        else if(BusName.equals("তরঙ্গ")){
            if(ScheduleTime.equals( "7:0 AM")) return "405" ;
            if(ScheduleTime.equals("7:30 AM")) return "404" ;
            if(ScheduleTime.equals("8:15 AM")) return "403" ;
            if(ScheduleTime.equals("9:00 AM")) return "402" ;
            if(ScheduleTime.equals("10:00 AM")) return "401" ;
        }
        return "NULL" ;
    }
    public static String getEnglishRouteName(String BusName){
        if(BusName.equals( "বৈশাখি")) return "boishakhi" ;
        else if(BusName.equals("চৈতালি"))   return "choitali";
        else if(BusName.equals("শ্রাবণ"))   return "shrabon";
        else if(BusName.equals("তরঙ্গ")) return "torongo" ;
        return BusName ;
    }
    public static String getBanglaRouteName(String BusName){
        if(BusName.equals("boishakhi")) return  "বৈশাখি";
        else if(BusName.equals("choitali"))    return "চৈতালি";
        else if(BusName.equals("shrabon"))    return "শ্রাবণ";
        else if(BusName.equals("torongo")) return "তরঙ্গ";
        return BusName ;
    }

}
