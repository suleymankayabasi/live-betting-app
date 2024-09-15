Here is the `README.md` in the correct format for your project:

---

# Canlı Bahis Uygulaması

Bu proje, kullanıcıların maç bültenindeki karşılaşmalar için aynı bahis türüne birden fazla kupon oynayabileceği canlı bahis uygulamasıdır. Uygulama dinamik olarak her maç için bahis oranları üretir ve oran değişimlerini gerçek zamanlı olarak takip etmeyi sağlar. Ayrıca, bir maça maksimum 500 kupon oynanabilir ve her kupon için 2 saniyelik bir timeout bulunmaktadır.

## Özellikler

1. **Maç Ekleme:**
   - Maç bültenine yeni maçlar eklenebilir.
   - Her maç için dinamik olarak bahis oranları oluşturulur.
   - Maç bilgileri: Lig, ev sahibi takım, deplasman takım, oranlar ve karşılaşma başlama zamanı.
   
2. **Gerçek Zamanlı Oran Güncelleme:**
   - Maçlar eklendikten sonra, bahis oranları her saniyede bir rastgele olarak değişir.
   
3. **Kupon Sistemi:**
   - Maksimum 500 kupon aynı anda bir maça oynanabilir.
   - Oran değişikliklerinden kuponlar etkilenmez.
   - Kupon için 2 saniyelik bir timeout bulunmaktadır (parametrik olarak değiştirilebilir).

## Teknolojiler

- **Spring Boot 3.X**
- **Java 17/21**
- **H2 Database**
- **Web Socket**
- **Docker**

## Uygulamayı Çalıştırma

### Seçenek 1: Yerel Olarak Çalıştırma

1. **Proje Dizini:**
   ```bash
   git clone https://github.com/suleymankayabasi/live-betting-app.git
   cd live-betting-app
   ```

2. **Projeyi Maven ile Derleme:**
   ```bash
   mvn clean package
   ```

3. **Spring Boot Uygulamasını Çalıştırma:**
   ```bash
   mvn spring-boot:run
   ```

4. **H2 Database Konsoluna Erişim (Opsiyonel):**
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:bettingdb`
   - User: `admin`
   - Password: `password  `

### Seçenek 2: Docker ile Çalıştırma

1. **Docker'ın kurulu olduğundan emin olun.**

2. **Docker İmajını Oluşturun:**
   ```bash
   docker-compose up --build
   ```

   Bu komut uygulama için Docker imajını oluşturur ve çalıştırır. Uygulamaya `http://localhost:8080` adresinden erişebilirsiniz.

## API Bitiş Noktaları

1. **Yeni Maç Ekleme (POST):**

   - URL: `http://localhost:8080/api/matches`
   - Örnek İstek:
     ```json
     {
       "league": "Premier League",
       "homeTeam": "Team A",
       "awayTeam": "Team B",
       "homeWinOdds": 1.85,
       "drawOdds": 3.1,
       "awayWinOdds": 4.2,
       "matchStartTime": "2024-09-15T15:00:00"
     }
     ```

2. **Tüm Maçları ve Gerçek Zamanlı Oranları Getir (GET):**

   - URL: `http://localhost:8080/api/matches`
   - Bu endpoint, tüm maçları ve bahis oranlarını gerçek zamanlı olarak döner.

3. **Kupon Oyna (POST):**

   - URL: `http://localhost:8080/api/coupons`
   - Örnek İstek:
     ```json
     {
       "bets": [
         {
           "matchId": 1,
           "selectedOutcome": "HOME_WIN"
         }
       ],
       "stake": 100,
       "repetitionCount": 2
     }
     ```

4. **Kuponu ID'ye Göre Getir (GET):**

   - URL: `http://localhost:8080/api/coupons/{couponId}`
   - Bu endpoint, belirtilen ID'ye sahip kuponun detaylarını döner.
    
5. **Canlı Bahis Oranlarını:**

   - URL: `http://localhost:8080/index.html`
   - Bu url, active olan maçların canlı bahis oranlarını döner.
## Yapılandırmalar

1. **Dinamik Oran Güncelleme Süresi:**
   Oranlar varsayılan olarak her saniyede bir güncellenir. Bu ayarı `application.properties` dosyasından değiştirebilirsiniz.

2. **Kupon Timeout Süresi:**
   Kupon oluşturma işlemi için timeout süresi varsayılan olarak 2 saniye olarak ayarlanmıştır. Bu ayarı `application.properties` üzerinden değiştirebilirsiniz:
   ```properties
   coupon.creation.timeout.seconds=2
   ```

## Dockerfile

```Dockerfile
# Amazon Corretto 17 tabanlı bir imaj kullan
FROM amazoncorretto:17-alpine

# Çalışma dizini ayarla
WORKDIR /app

# JAR dosyasını kopyala
COPY target/live-betting-app.jar app.jar

# 8080 portunu aç
EXPOSE 8080

# Uygulamayı başlat
ENTRYPOINT ["java", "-jar", "app.jar"]
```

## docker-compose.yml

```yaml
version: '3.8'

services:
  live-betting-app:
    build:
      context: .
      dockerfile: Dockerfile
    ports:
      - "8080:8080"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
```

## Testler

Birim testleri çalıştırmak için aşağıdaki Maven komutunu kullanabilirsiniz:

```bash
mvn test
```

## Sonuç

Bu canlı bahis uygulaması, maçlar eklendikten sonra dinamik bahis oranlarını gerçek zamanlı olarak günceller ve aynı anda birden fazla kupon oynamanızı sağlar. Uygulama Spring Boot ve H2 Database kullanarak hızlı ve etkin veri yönetimi sağlar.

---

Let me know if you need further adjustments or clarification!
