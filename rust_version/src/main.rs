mod application;
mod config;
mod window;

use crate::application::SocketTrenchApplication;
use crate::window::SocketTrenchWindow;

use crate::config::*;
use gettextrs::{bind_textdomain_codeset, bindtextdomain, textdomain};
use gtk4::{gio, glib};
use gtk4::prelude::*;

fn main() -> glib::ExitCode {
    bindtextdomain(GETTEXT_PACKAGE, LOCALEDIR).expect("Unable to bind the text domain");
    bind_textdomain_codeset(GETTEXT_PACKAGE, "UTF-8").expect("Unable to set the text domain encoding");
    textdomain(GETTEXT_PACKAGE).expect("Unable to switch to the text domain");
    let resources = gio::Resource::load(PKGDATADIR.to_owned() + "/socket_trench.gresource").expect("Could not load resources");
    gio::resources_register(&resources);
    let app = SocketTrenchApplication::new("com.joao_arthur.socket_trench", &gio::ApplicationFlags::empty());
    app.run()
}
