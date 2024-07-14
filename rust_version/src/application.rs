use gtk::prelude::*;
use adw::subclass::prelude::*;
use gtk::{gio, glib};

use crate::config::VERSION;
use crate::SocketTrenchWindow;

mod imp {
    use super::*;

    #[derive(Debug, Default)]
    pub struct SocketTrenchApplication {}

    #[glib::object_subclass]
    impl ObjectSubclass for SocketTrenchApplication {
        const NAME: &'static str = "SocketTrenchApplication";
        type Type = super::SocketTrenchApplication;
        type ParentType = adw::Application;
    }

    impl ObjectImpl for SocketTrenchApplication {
        fn constructed(&self) {
            self.parent_constructed();
            let obj = self.obj();
            obj.setup_gactions();
            obj.set_accels_for_action("app.quit", &["<primary>q"]);
        }
    }

    impl ApplicationImpl for SocketTrenchApplication {
        fn activate(&self) {
            let application = self.obj();
            let window = if let Some(window) = application.active_window() {
                window
            } else {
                let window = SocketTrenchWindow::new(&*application);
                window.upcast()
            };
            window.present();
        }
    }

    impl GtkApplicationImpl for SocketTrenchApplication {}
    impl AdwApplicationImpl for SocketTrenchApplication {}
}

glib::wrapper! {
    pub struct SocketTrenchApplication(ObjectSubclass<imp::SocketTrenchApplication>)
        @extends gio::Application, gtk::Application, adw::Application,
        @implements gio::ActionGroup, gio::ActionMap;
}

impl SocketTrenchApplication {
    pub fn new(application_id: &str, flags: &gio::ApplicationFlags) -> Self {
        glib::Object::builder()
            .property("application-id", application_id)
            .property("flags", flags)
            .build()
    }

    fn setup_gactions(&self) {
        let quit_action = gio::ActionEntry::builder("quit")
            .activate(move |app: &Self, _, _| app.quit())
            .build();
        let about_action = gio::ActionEntry::builder("about")
            .activate(move |app: &Self, _, _| app.show_about())
            .build();
        self.add_action_entries([quit_action, about_action]);
    }

    fn show_about(&self) {
        let window = self.active_window().unwrap();
        let about = adw::AboutWindow::builder()
            .transient_for(&window)
            .application_name("socket_trench")
            .application_icon("com.joao_arthur.socket_trench")
            .developer_name("Unknown")
            .version(VERSION)
            .developers(vec!["Unknown"])
            .copyright("© 2024 Unknown")
            .build();

        about.present();
    }
}
