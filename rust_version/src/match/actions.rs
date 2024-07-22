use relm4::actions::{RelmAction, RelmActionGroup};
use relm4::prelude::*;

use super::AppModel;

relm4::new_action_group!(pub AppActions, "app");
relm4::new_stateless_action!(pub ShowAbout, AppActions, "about");

impl AppModel {
    pub fn create_actions(
        widgets: &<Self as SimpleComponent>::Widgets,
        sender: &ComponentSender<Self>,
    ) {
        let mut app_actions = RelmActionGroup::<AppActions>::new();
        let show_about = {
            let sender = sender.clone();
            RelmAction::<ShowAbout>::new_stateless(move |_| {
                sender.input(<Self as SimpleComponent>::Input::ShowAboutWindow);
            })
        };
        app_actions.add_action(show_about);
        app_actions.register_for_widget(&widgets.main_window);
    }
}
