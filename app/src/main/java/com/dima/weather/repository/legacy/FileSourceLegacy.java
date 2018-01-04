package com.dima.weather.repository.legacy;

import android.content.res.AssetManager;

import com.dima.weather.model.City;
import com.dima.weather.repository.FileSourceRepository;
import com.dima.weather.util.Translate;

import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import io.reactivex.Observable;


/**
 * Created by ishabaev on 21.07.16.
 */
public class FileSourceLegacy implements FileSourceRepository {

    private AssetManager mAssetManager;
    private final static String FILE_NAME = "city_list.txt";
    private static final int MIN_LENGTH = 2;

    public FileSourceLegacy(AssetManager assetManager) {
        mAssetManager = assetManager;
    }


    @Override
    public Observable<City> searchCity(String cityName) {
        return Observable.create(sub -> {
                    if (cityName.isEmpty()) {
                        sub.onComplete();
                        return;
                    }
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
                        if (count > 0) {
                            sub.onComplete();
                        } else {
                            sub.onError(new Exception());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        sub.onError(e);
                    }
                }
        );
    }


    @Override
    public Observable<List<City>> searchCities(String cityName) {
        if (cityName.isEmpty() || cityName.length() < MIN_LENGTH)
            return Observable.just(Collections.emptyList());
        LinkedList<City> result = new LinkedList<>();
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
                        line.toLowerCase().contains(cityName.toLowerCase())) {
                    String[] cityParams = line.split("\t");
                    final City city = new City();
                    city.setId(Long.parseLong(cityParams[0]));
                    city.setName(cityParams[1]);
                    city.setLat(Double.parseDouble(cityParams[2]));
                    city.setLon(Double.parseDouble(cityParams[3]));
                    city.setCountry(cityParams[4]);
                    count++;
                    result.add(city);
                }
                if (count > 20) {
                    break;
                }
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Observable.error(e);
        }

        return Observable.just(result);
    }


}
