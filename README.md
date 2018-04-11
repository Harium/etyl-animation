# etyl-animation
Animation module to Etyl

## Maven
```
<dependency>
    <groupId>com.harium.etyl</groupId>
    <artifactId>animation</artifactId>
    <version>1.3.3</version>
</dependency>
```

## Gradle
```
compile 'com.harium.etyl:animation:1.3.3'
```

## Fluent API Example
```
int duration = 1000; // 1 second
Layer layer = new Layer(0,0,10,10);

// Move layer from position (0,0) to (200,80) in 1 second
Animation.animate(layer).moveX(duration).from(0, 0).to(200, 80).interpolate(Interpolator.LINEAR).start();
```