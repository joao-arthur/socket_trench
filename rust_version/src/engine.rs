use relm4::gtk::gdk_pixbuf::Pixbuf;

use crate::app::{SCREEN_HEIGHT, SCREEN_WIDTH};

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

#[derive(Debug)]
pub struct State {
    pub gos: Vec<GameObject>,
    gos_c: Vec<GameObject>,
    gos_d: Vec<GameObject>,
}

impl State {
    pub fn from(gos: Vec<GameObject>) -> Self {
        State {
            gos,
            gos_c: Vec::new(),
            gos_d: Vec::new(),
        }
    }

    pub fn create(&mut self, go: GameObject) {
        self.gos_c.push(go)
    }

    pub fn delete(&mut self, go: GameObject) {
        self.gos_d.push(go)
    }

    pub fn apply(&mut self) {
        for i_d in 0..self.gos_d.len() {
            for i in 0..self.gos.len() {
                if self.gos[i] == self.gos_d[i_d] {
                    self.gos.remove(i);
                    break;
                }
            }
        }
        self.gos_d.clear();
        self.gos.append(&mut self.gos_c);
    }
}

#[derive(Debug, PartialEq)]
pub struct GameObject {
    pub body: Option<BoxDim>,
    pub collider: Option<BoxDim>,
    pub bounds: Option<BoxPos>,
    pub texture: Option<Pixbuf>,
    pub force: Option<Point>,
    // pub on_key_pressed: Box<dyn Fn(i32, &State) + 'static>,
    // pub on_key_released: Box<dyn Fn(i32, &State) + 'static>,
    // pub on_collide_with: Box<dyn Fn(&mut GameObject, &State) + 'static>,
    // pub on_leave_screen: Box<dyn Fn(&State) + 'static>,
}   

impl Default for GameObject {
    fn default() -> Self {
        GameObject {
            body: None,
            collider: None,
            bounds: None,
            texture: None,
            force: None,
        //    on_key_pressed: Box::new(|_, __| {}),
        //    on_key_released: Box::new(|_, __| {}),
        //    on_collide_with: Box::new(|_, __| {}),
        //    on_leave_screen: Box::new(|_| {}),
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

fn apply_leave_screen(go: &mut GameObject) {
    if let Some(body) = &mut go.body {
        if 
            body.x + body.w < 0 ||
            body.y + body.h < 0 ||
            body.x > SCREEN_WIDTH.into() ||
            body.y > SCREEN_HEIGHT.into()
        {
         //   (go.on_leave_screen)(state)
        }
    }
}

fn apply_collisions_in_collider(state: &mut State) {
    for i in 0..state.gos.len() {
        let a = &state.gos[i];
        if let Some(collider_a) = &a.collider {
            for j in i+1..state.gos.len() {
                let mut b = &mut state.gos[j];
                if let Some(collider_b) = &b.collider {
                    if 
                        collider_a.x < collider_b.x + collider_b.w &&
                        collider_a.x + collider_a.w > collider_b.x &&
                        collider_a.y < collider_b.y + collider_b.h &&
                        collider_a.y + collider_a.h > collider_b.y
                    {
                        (a.on_collide_with)(&mut b, state);
                    }
                }
            }
        }
    }
}

struct Manager {
    state: State,
}

impl Manager {
    pub fn on_update(self: &mut Self) {
        self.state.apply();
        for go in &mut self.state.gos {
            apply_physics_in_body(go);
        }
        for go in &mut self.state.gos {
            apply_leave_screen(go);
        }
        apply_collisions_in_collider(&self.state)
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
        let mut go = GameObject {
            ..Default::default()
        };
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

    #[test]
    fn test_apply_collisions_in_collider_empty() {
        let  go1 = GameObject {
            ..Default::default()
        };
        let  go2 = GameObject {
            ..Default::default()
        };
        let gos = vec![go1, go2];
        let state = State::from(gos);
        apply_collisions_in_collider(&state);
        assert_eq!(state.gos[0].body, None);
        assert_eq!(state.gos[1].body, None);
    }

    #[test]
    fn test_apply_collisions_in_collider() {
        let acc = 0;

        let go1 = GameObject {
            on_collide_with: |other: &GameObject, state: &State| {
                acc += 1;
            },
            ..Default::default()
        };
        let go2 = GameObject {
            on_collide_with: |other: &GameObject, state: &State| {
                acc += 1;
            },
            ..Default::default()
        };
        let gos = vec![go1, go2];
        let state = State::from(gos);
        apply_collisions_in_collider(&state);
    }

    fn on_collide_delete(other: &mut GameObject, state: &State) {
                state.delete(other);

    }

    #[test]
    fn test_apply_collisions_in_collider_delete() {
        let go1 = GameObject {
            on_collide_with: on_collide_delete,
            ..Default::default()
        };
        let go2 = GameObject {
            ..Default::default()
        };
        let gos = vec![go1, go2];
        let state = State::from(gos);
        apply_collisions_in_collider(&state);
    }

    #[test]
    fn test_state() {
        let mut state = State::from(vec![
            GameObject {
                body: Some(BoxDim::from(1, 2, 3, 4)),
                ..Default::default()
            },
            GameObject {
                body: Some(BoxDim::from(2, 3, 4, 5)),
                ..Default::default()
            },
        ]);
        assert_eq!(
            state.gos,
            vec![
                GameObject {
                    body: Some(BoxDim::from(1, 2, 3, 4)),
                    ..Default::default()
                },
                GameObject {
                    body: Some(BoxDim::from(2, 3, 4, 5)),
                    ..Default::default()
                }
            ]
        );
        state.create(GameObject {
            body: Some(BoxDim::from(3, 4, 5, 6)),
            ..Default::default()
        });
        state.create(GameObject {
            body: Some(BoxDim::from(4, 5, 6, 7)),
            ..Default::default()
        });
        assert_eq!(
            state.gos,
            vec![
                GameObject {
                    body: Some(BoxDim::from(1, 2, 3, 4)),
                    ..Default::default()
                },
                GameObject {
                    body: Some(BoxDim::from(2, 3, 4, 5)),
                    ..Default::default()
                }
            ]
        );
        state.delete(GameObject {
            body: Some(BoxDim::from(2, 3, 4, 5)),
            ..Default::default()
        });
        state.apply();
        assert_eq!(
            state.gos,
            vec![
                GameObject {
                    body: Some(BoxDim::from(1, 2, 3, 4)),
                    ..Default::default()
                },
                GameObject {
                    body: Some(BoxDim::from(3, 4, 5, 6)),
                    ..Default::default()
                },
                GameObject {
                    body: Some(BoxDim::from(4, 5, 6, 7)),
                    ..Default::default()
                },
            ]
        );
        state.apply();
        assert_eq!(
            state.gos,
            vec![
                GameObject {
                    body: Some(BoxDim::from(1, 2, 3, 4)),
                    ..Default::default()
                },
                GameObject {
                    body: Some(BoxDim::from(3, 4, 5, 6)),
                    ..Default::default()
                },
                GameObject {
                    body: Some(BoxDim::from(4, 5, 6, 7)),
                    ..Default::default()
                },
            ]
        );
    }
}
