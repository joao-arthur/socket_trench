use app::window::WindowModel;
use relm4::prelude::*;
use sys_locale::get_locale;

mod app;
mod constants;

rust_i18n::i18n!("locales", fallback = "en-US");

fn main() {
    let locale = get_locale().unwrap_or_else(|| String::from("en-US"));
    rust_i18n::set_locale(&locale);
    let app = RelmApp::new(constants::APP_ID);
    app.run::<WindowModel>(());
}
