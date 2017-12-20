package com.dima.weather.repository.legacy;

import android.content.res.AssetManager;

import com.dima.weather.model.City;
import com.dima.weather.repository.LocaleRepository;
import com.dima.weather.util.Translate;

import java.io.InputStream;
import java.util.Scanner;

import io.reactivex.Observable;


/**
 * Created by ishabaev on 21.07.16.
 */
public class FileSourceLegacy implements LocaleRepository {

    private AssetManager mAssetManager;
    private final static String FILE_NAME = "city_list.txt";

    private FileSourceLegacy(AssetManager assetManager) {
        mAssetManager = assetManager;
    }


    @Override
    public Observable<City> searchCity(String cityName) {
        return Observable.create( sub -> {
                    try {
                        InputStream is = mAssetManager.open(FILE_NAME);
                        Scanner scanner = new Scanner(is);
                        int count = 0;
                        int row = 0;
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            if (row == 0) {
                                row++;
                                continue;
                            }
                            if (line.toLowerCase().contains(cityName.toLowerCase()) ||
                                    line.toLowerCase().contains(Translate.ru2en(cityName.toLowerCase()))) {
                                String[] cityParams = line.split("\t");
                                final City city = new City();
                                city.setId(Long.parseLong(cityParams[0]));
                                city.setName(cityParams[1]);
                                city.setLat(Double.parseDouble(cityParams[2]));
                                city.setLon(Double.parseDouble(cityParams[3]));
                                city.setCountry(cityParams[4]);
                                count++;
                                sub.onNext(city);
                            }
                            if (count > 10) {
                                sub.onComplete();
                                break;
                            }
                            row++;
                        }
                        if(count > 0) {
                            sub.onComplete();
                        }else {
                            sub.onError(new Exception());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sub.onError(e);
                    }
                }
        );
    }
}
