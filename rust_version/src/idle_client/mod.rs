use gtk::prelude::*;
use relm4::prelude::*;

pub struct IdleClientModel {}

pub struct IdleClientInit {}

#[derive(Debug)]
pub struct IdleClientInput {}

#[derive(Debug)]
pub struct IdleClientOutput {}

#[relm4::component(pub)]
impl SimpleComponent for IdleClientModel {
    type Init = ();
    type Input = IdleClientInput;
    type Output = IdleClientOutput;

    fn init(
        init: Self::Init,
        root: &Self::Root,
        sender: ComponentSender<Self>,
    ) -> ComponentParts<Self> {
        let model = IdleClientModel {};
        let widgets = view_output!();

        ComponentParts { model, widgets }
    }

    fn update(&mut self, message: Self::Input, sender: ComponentSender<Self>) {}

    view! {
        gtk::Label {
            set_label: "Diacho"
        }
    }
}
