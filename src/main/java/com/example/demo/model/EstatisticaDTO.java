package com.example.demo.model;

import java.util.DoubleSummaryStatistics;

public class EstatisticaDTO {
    private long count;
    private double sum;
    private double average;
    private double min;
    private double max;

    public EstatisticaDTO() {}

    public EstatisticaDTO(long count, double sum, double average, double min, double max) {
        this.count = count;
        this.sum = sum;
        this.average = average;
        this.min = min;
        this.max = max;
    }

    public static EstatisticaDTO from(DoubleSummaryStatistics stats) {
        EstatisticaDTO dto = new EstatisticaDTO();
        dto.setCount(stats.getCount());
        dto.setSum(stats.getSum());
        dto.setMin(stats.getCount() > 0 ? stats.getMin() : 0);
        dto.setMax(stats.getCount() > 0 ? stats.getMax() : 0);
        dto.setAverage(stats.getCount() > 0 ? stats.getAverage() : 0);
        return dto;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public double getAverage() {
        return average;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }
}
