use gtk::prelude::*;
use adw::subclass::prelude::*;
use gtk::{gio, glib};

mod imp {
    use super::*;

    #[derive(Debug, Default, gtk::CompositeTemplate)]
    #[template(resource = "/com/joao_arthur/socket_trench/window.ui")]
    pub struct SocketTrenchWindow {
        #[template_child]
        pub header_bar: TemplateChild<adw::HeaderBar>,
        #[template_child]
        pub label: TemplateChild<gtk::Label>,
    }

    #[glib::object_subclass]
    impl ObjectSubclass for SocketTrenchWindow {
        const NAME: &'static str = "SocketTrenchWindow";
        type Type = super::SocketTrenchWindow;
        type ParentType = adw::ApplicationWindow;

        fn class_init(klass: &mut Self::Class) {
            klass.bind_template();
        }

        fn instance_init(obj: &glib::subclass::InitializingObject<Self>) {
            obj.init_template();
        }
    }

    impl ObjectImpl for SocketTrenchWindow {}
    impl WidgetImpl for SocketTrenchWindow {}
    impl WindowImpl for SocketTrenchWindow {}
    impl ApplicationWindowImpl for SocketTrenchWindow {}
    impl AdwApplicationWindowImpl for SocketTrenchWindow {}
}

glib::wrapper! {
    pub struct SocketTrenchWindow(ObjectSubclass<imp::SocketTrenchWindow>)
        @extends gtk::Widget, gtk::Window, gtk::ApplicationWindow, adw::ApplicationWindow,        @implements gio::ActionGroup, gio::ActionMap;
}

impl SocketTrenchWindow {
    pub fn new<P: glib::IsA<gtk::Application>>(application: &P) -> Self {
        glib::Object::builder()
            .property("application", application)
            .build()
    }
}
