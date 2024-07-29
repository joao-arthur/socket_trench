use relm4::gtk::gdk_pixbuf::Pixbuf;

#[derive(Debug, PartialEq)]
pub struct BoxDim {
    pub x: i32,
    pub y: i32,
    pub w: i32,
    pub h: i32,
}

impl BoxDim {
    pub fn from(x: i32, y: i32, w: i32, h: i32) -> Self {
        BoxDim { x, y, w, h }
    }
}

#[derive(Debug, PartialEq)]
pub struct BoxPos {
    pub x1: i32,
    pub y1: i32,
    pub x2: i32,
    pub y2: i32,
}

impl BoxPos {
    pub fn from(x1: i32, y1: i32, x2: i32, y2: i32) -> Self {
        BoxPos { x1, y1, x2, y2 }
    }
}

#[derive(Debug, PartialEq)]
pub struct Point {
    pub x: i32,
    pub y: i32,
}

impl Point {
    pub fn from(x: i32, y: i32) -> Self {
        Point { x, y }
    }
}

#[derive(Debug, PartialEq)]
pub struct GameObject {
    pub body: Option<BoxDim>,
    pub collider: Option<BoxDim>,
    pub bounds: Option<BoxPos>,
    pub texture: Option<Pixbuf>,
    pub force: Option<Point>,
}

impl Default for GameObject {
    fn default() -> Self {
        GameObject {
            body: None,
            collider: None,
            bounds: None,
            texture: None,
            force: None,
        }
    }
}

fn apply_physics_in_body(go: &mut GameObject) {
    if let Some(body) = &mut go.body {
        if let Some(force) = &go.force {
            body.x += force.x;
            body.y += force.y;
            if let Some(bounds) = &go.bounds {
                if body.x < bounds.x1 {
                    body.x = bounds.x1;
                }
                if body.x > bounds.x2 - body.w {
                    body.x = bounds.x2 - body.w;
                }
                if body.y < bounds.y1 {
                    body.y = bounds.y1;
                }
                if body.y > bounds.y2 - body.h {
                    body.y = bounds.y2 - body.h;
                }
            }
        }
    }
}

#[cfg(test)]
mod test {
    use super::*;

    #[test]
    fn test_box_dim() {
        let bd = BoxDim::from(11, 22, 33, 44);
        assert_eq!(bd.x, 11);
        assert_eq!(bd.y, 22);
        assert_eq!(bd.w, 33);
        assert_eq!(bd.h, 44);
    }

    #[test]
    fn test_box_pos() {
        let bp = BoxPos::from(11, 22, 33, 44);
        assert_eq!(bp.x1, 11);
        assert_eq!(bp.y1, 22);
        assert_eq!(bp.x2, 33);
        assert_eq!(bp.y2, 44);
    }

    #[test]
    fn test_point() {
        let p = Point::from(11, 22);
        assert_eq!(p.x, 11);
        assert_eq!(p.y, 22);
    }

    #[test]
    fn test_apply_physics_empty() {
        let mut go = GameObject { ..Default::default() };
        apply_physics_in_body(&mut go);
        assert_eq!(go.body, None);
    }

    #[test]
    fn test_apply_physics_go_body() {
        let mut go = GameObject {
            body: Some(BoxDim::from(1, 2, 3, 4)),
            ..Default::default()
        };
        apply_physics_in_body(&mut go);
        assert_eq!(go.body, Some(BoxDim::from(1, 2, 3, 4)));
    }

    #[test]
    fn test_apply_physics_go_body_force() {
        let mut go = GameObject {
            body: Some(BoxDim::from(1, 2, 3, 4)),
            force: Some(Point::from(1, 2)),
            ..Default::default()
        };
        apply_physics_in_body(&mut go);
        assert_eq!(go.body, Some(BoxDim::from(2, 4, 3, 4)));
        apply_physics_in_body(&mut go);
        assert_eq!(go.body, Some(BoxDim::from(3, 6, 3, 4)));
        apply_physics_in_body(&mut go);
        assert_eq!(go.body, Some(BoxDim::from(4, 8, 3, 4)));
        apply_physics_in_body(&mut go);
        assert_eq!(go.body, Some(BoxDim::from(5, 10, 3, 4)));
    }

    #[test]
    fn test_apply_physics_go_body_force_bounds() {
        let mut go = GameObject {
            body: Some(BoxDim::from(32, 32, 20, 20)),
            force: Some(Point::from(-10, -10)),
            bounds: Some(BoxPos::from(0, 0, 100, 100)),
            ..Default::default()
        };
        apply_physics_in_body(&mut go);
        assert_eq!(go.body, Some(BoxDim::from(22, 22, 20, 20)));
        apply_physics_in_body(&mut go);
        assert_eq!(go.body, Some(BoxDim::from(12, 12, 20, 20)));
        apply_physics_in_body(&mut go);
        assert_eq!(go.body, Some(BoxDim::from(2, 2, 20, 20)));
        apply_physics_in_body(&mut go);
        assert_eq!(go.body, Some(BoxDim::from(0, 0, 20, 20)));
    }
}
