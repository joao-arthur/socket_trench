use relm4::adw::prelude::*;
use relm4::prelude::*;

pub struct MatchPlayModel;

#[relm4::component(pub)]
impl SimpleComponent for MatchPlayModel {
    type Init = ();
    type Input = ();
    type Output = ();

    view! {
        #[root]
        gtk::Box {
            set_orientation: gtk::Orientation::Vertical,
            set_hexpand: true,

            #[name = "drawing"]
            gtk::DrawingArea {
                set_content_width: 700,
                set_content_height: 500,
            },
        }
    }

    fn init(
        _init: Self::Init,
        root: &Self::Root,
        sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let model = MatchPlayModel {};
        let widgets = view_output!();
        let path = std::path::Path::new("./resources/background.png");
        let pixbuf = gtk::gdk_pixbuf::Pixbuf::from_file(path).unwrap();
        widgets.drawing.set_draw_func(
            move |area: &gtk::DrawingArea, context: &gtk::cairo::Context, w, h| {
                context.set_source_pixbuf(&pixbuf, 0.0, 0.0);
                context.paint().expect("dfuhi");
                context.stroke().expect("uiedrfhuiefr");
            },
        );

        ComponentParts { model, widgets }
    }
}
