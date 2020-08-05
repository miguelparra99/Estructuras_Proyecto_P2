// 
// Decompiled by Procyon v0.5.36
// 

package Apoyo;

public class HuffmanInfo
{
    private String text;
    private int frecuencia;
    private Long bit;
    
    public HuffmanInfo() {
    }
    
    public HuffmanInfo(final String text, final int frecuencia, final Long bit) {
        this.text = text;
        this.frecuencia = frecuencia;
        this.bit = bit;
    }
    
    public HuffmanInfo(final String text, final int frecuencia) {
        this.text = text;
        this.frecuencia = frecuencia;
    }
    
    public String getText() {
        return this.text;
    }
    
    public void setText(final String text) {
        this.text = text;
    }
    
    public int getFrecuencia() {
        return this.frecuencia;
    }
    
    public void setFrecuencia(final int frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    public Long getBit() {
        return this.bit;
    }
    
    public void setBit(final Long bit) {
        this.bit = bit;
    }

    @Override
    public String toString() {
        return "HuffmanInfo{" + "text=" + text + ", frecuencia=" + frecuencia + ", bit=" + bit + '}';
    }
    
    
    
    
    
    
    
}
