package org.backend.backend.util;

import org.backend.backend.exception.PointValidationException;
import org.backend.backend.model.Point;
import org.springframework.stereotype.Service;

@Service
public class PointResultChecker {
    public boolean checkResult(Point point) throws PointValidationException {
        float x = point.getX();
        float y = point.getY();
        float r = point.getRadius();
        if (!validateX(x) || !validateY(y) || !validateRadius(r)) throw new PointValidationException("Can not pass validation test");
        return (checkTriangle(x,y,r) || checkCircle(x,y,r) || checkRectangle(x,y,r));
    }

    private boolean validateRadius(float r) {
        return r > 0 && r <= 5;
    }

    private boolean validateY(float y){
        return y >= -3 && y <= 5;
    }

    private boolean validateX(float x){
        return x >= -3 && x <= 5;
    }

    private boolean checkRectangle(float x, float y, float r){
        return (y >= 0 && y <= r && x >= -r && x <= 0);
    }

    private boolean checkTriangle(float x, float y, float r){
        return (x - r <= y && x >= 0 && y <= 0);
    }

    private boolean checkCircle(float x, float y, float r){
        return ((x * x) + (y * y) <= (r * r) && x <= 0 && y <= 0);
    }
}
