package com.example.mape0515.myapplication3;

import android.util.Log;

import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.ExecutionException;


class WeatherHandler {
    private String TAG = "MAPE";

    String weatherHandler() throws ParseException, XmlPullParserException, IOException, ExecutionException, InterruptedException {
        //  String weatherHandler(String region, int tempMin, int tempMax)
        int tempP = 0, plessure = 0, humidity = 0, wind = 0;
        APISupport ap = new APISupport();
        Map<String, Integer> weatherMap = ap.parser("Voronezh");
        Log.e(TAG, "weatherMap" + weatherMap.size());
        for (Map.Entry entry : weatherMap.entrySet()) {
            Log.e(TAG, "entry" + entry.getKey());

            if (entry.getKey().equals("temp_c")) {
                tempP = (int) entry.getValue();
            }
            if (entry.getKey().equals("pressure_mb")) {
                plessure = (int) entry.getValue();
            }
            if (entry.getKey().equals("relative_humidity")) {
                humidity = (int) entry.getValue();
            }
            if (entry.getKey().equals("wind_kph")) {
                wind = (int) entry.getValue();
            }
        }

        DateFormat dateFormatH = new SimpleDateFormat("HH");
        Date hours = new Date();
        int currentHour = Integer.parseInt(dateFormatH.format(hours));
        Log.e(TAG, "currentHour" + currentHour);
        DateFormat dateFormatM = new SimpleDateFormat("MM");
        Date mounth = new Date();
        int currentMounth = Integer.parseInt(dateFormatM.format(mounth));
        Log.e(TAG, "currentMounth" + currentMounth);

        if (currentMounth > 8 && currentMounth < 12) {
            if (currentHour > 6 && currentHour < 12) {
                if (tempP > -3 && tempP < 20) {
                    return String.valueOf(true);
                }
            } else {
                if (currentHour >= 12 && currentHour < 17) {
                    if (tempP > 5 && tempP < 25) {
                        return String.valueOf(true);
                    }
                } else {
                    if (currentHour >= 17 && currentHour < 20) {
                        if (tempP > 0 && tempP < 20) {
                            return String.valueOf(true);
                        }
                    } else {
                        if (tempP > -5 && tempP < 20) {
                            return String.valueOf(true);
                        }
                    }
                }
            }
        } else {
            if (currentMounth >= 1 || currentMounth == 12) {
                if (currentHour > 6 && currentHour < 12) {
                    if (tempP > -15 && tempP < 20) {
                        return String.valueOf(true);
                    }
                } else {
                    if (currentHour >= 12 && currentHour < 17) {
                        if (tempP > -10 && tempP < 25) {
                            return String.valueOf(true);
                        }
                    } else {
                        if (currentHour >= 17 && currentHour < 20) {
                            if (tempP > -15 && tempP < 20) {
                                return String.valueOf(true);
                            }
                        } else {
                            if (tempP > -20 && tempP < 20) {
                                return String.valueOf(true);
                            }
                        }
                    }
                }

            } else {
                if (currentMounth >= 3 || currentMounth < 6) {
                    if (currentHour > 6 && currentHour < 12) {
                        if (tempP > 5 && tempP < 25) {
                            return String.valueOf(true);
                        }
                    } else {
                        if (currentHour >= 12 && currentHour < 17) {
                            if (tempP > 7 && tempP < 25) {
                                return String.valueOf(true);
                            }
                        } else {
                            if (currentHour >= 17 && currentHour < 20) {
                                if (tempP > 5 && tempP < 30) {
                                    return String.valueOf(true);
                                }
                            } else {
                                if (tempP > 0 && tempP < 20) {
                                    return String.valueOf(true);
                                }
                            }
                        }
                    }

                } else {
                    if (currentMounth >= 6 || currentMounth > 9) {
                        if (currentHour > 6 && currentHour < 12) {
                            if (tempP > 15 && tempP < 25) {
                                return String.valueOf(true);
                            }
                        } else {
                            if (currentHour >= 12 && currentHour < 17) {
                                if (tempP > 15 && tempP < 30) {
                                    return String.valueOf(true);
                                }
                            } else {
                                if (currentHour >= 17 && currentHour < 20) {
                                    if (tempP > 15 && tempP < 25) {
                                        return String.valueOf(true);
                                    }
                                } else {
                                    if (tempP > 15 && tempP < 20) {
                                        return String.valueOf(true);
                                    }
                                }
                            }
                        }

                    }
                }
            }
        }
        return (String.valueOf(false));
    }

    public String detailedWeather() throws
            ParseException, XmlPullParserException, IOException, ExecutionException, InterruptedException {
        APISupport ap = new APISupport();
        Map<String, Integer> detailedWeatherMap = ap.parser("Voronezh");
        String currentkey, currentValue = null;
        for (Map.Entry entry : detailedWeatherMap.entrySet()) {
            if (entry.getValue() != null) {
                if (entry.getKey().equals("temp_c")) {
                    currentkey = "Temperature";
                    currentValue = entry.getValue().toString();
                }

            }
        }

        return currentValue;

    }
}
