use gtk4::prelude::*;
use libadwaita::subclass::prelude::*;
use gtk4::{gio, glib};

use crate::config::*;
use crate::SocketTrenchWindow;

mod imp {
    use super::*;

    #[derive(Debug, Default)]
    pub struct SocketTrenchApplication {}

    #[glib::object_subclass]
    impl ObjectSubclass for SocketTrenchApplication {
        const NAME: &'static str = "SocketTrenchApplication";
        type Type = super::SocketTrenchApplication;
        type ParentType = libadwaita::Application;
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
        @extends gio::Application, gtk4::Application, libadwaita::Application,
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
        let about = libadwaita::AboutWindow::builder()
            .transient_for(&window)
            .application_name(GETTEXT_PACKAGE)
            .application_icon("com.joao_arthur.socket_trench")
            .website("https://github.com/joao-arthur/socket_trench")
            .issue_url("https://github.com/joao-arthur/socket_trench/issues")
            .developer_name("João Arthur")
            .version(VERSION)
            .developers(vec!["João Arthur <joao.lothamer@gmail.com>"])
            .copyright("© 2024 João Arthur")
            .license_type(gtk4::License::Agpl30Only)
            .build();

        about.present();
    }
}
